package com.miniprac.board.service;

import com.miniprac.board.domain.Board;
import com.miniprac.board.domain.BoardFile;
import com.miniprac.board.domain.BoardLike;
import com.miniprac.board.domain.repository.BoardFileRepository;
import com.miniprac.board.domain.repository.BoardLikeRepository;
import com.miniprac.board.domain.repository.BoardRepository;
import com.miniprac.board.dto.BoardRequest;
import com.miniprac.board.service.exception.BoardNotFoundException;
import com.miniprac.common.dto.PageDto;
import com.miniprac.common.exception.PermissionException;
import com.miniprac.file.domain.File;
import com.miniprac.file.service.FileService;
import com.miniprac.school.domain.School;
import com.miniprac.school.domain.repository.SchoolRepository;
import com.miniprac.school.service.exception.SchoolNotFoundException;
import com.miniprac.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final BoardFileRepository boardFileRepository;
    private final BoardLikeRepository boardLikeRepository;
    private final SchoolRepository schoolRepository;

    private final FileService fileService;

    public Board create(BoardRequest.Create request) {
        School school = schoolRepository.findByName(request.getSchool()).orElseThrow(SchoolNotFoundException::new);
        List<File> files = fileService.uploadFiles(request.getFiles());
        List<BoardFile> boardFiles = files.stream().map(BoardFile::create).collect(Collectors.toList());
        Board board = Board.create(request, school, boardFiles);

        return boardRepository.save(board);
    }

    @Transactional(readOnly = true)
    public Page<Board> getListBySchoolAndDate(BoardRequest.GetListBySchoolAndDate request, PageDto pageDto) {
        return boardRepository.findAllBySchoolAndDate(request, pageDto.of());
    }

    @Transactional(readOnly = true)
    public Board getOne(Long boardId) {
        return boardRepository.findById(boardId).orElseThrow(BoardNotFoundException::new);
    }

    public Board update(BoardRequest.Update request, Long boardId, User user) {
        Board board = boardRepository.findById(boardId).orElseThrow(BoardNotFoundException::new);
        School school = schoolRepository.findByName(request.getSchool()).orElseThrow(SchoolNotFoundException::new);
        permissionCheck(user, board);

        if (request.isFileChanged()) {
            boardFileDelete(board);
            if (request.getFiles() != null) {
                List<File> fileList = fileService.uploadFiles(request.getFiles());

                permissionCheck(user, board);

                List<BoardFile> routeReviewFiles = fileList.stream()
                        .map(f -> BoardFile.builder().file(f).board(board).build())
                        .collect(Collectors.toList());
                boardFileRepository.saveAll(routeReviewFiles);
            }
        }
        board.update(request, school);

        return board;
    }

    public void deleteBoard(Long boardId, User user) {
        Board board = boardRepository.findById(boardId).orElseThrow(BoardNotFoundException::new);
        permissionCheck(user, board);
        boardFileDelete(board);
        boardRepository.delete(board);
    }

    public void createLike(Long boardId, User user) {
        Optional<BoardLike> findBoardLike = boardLikeRepository.findByBoardIdAndCreatedById(boardId, user.getId());
        Board board = boardRepository.findById(boardId).orElseThrow(BoardNotFoundException::new);

        if (findBoardLike.isEmpty()) {
            BoardLike boardLike = BoardLike.create(board);
            boardLikeRepository.save(boardLike);
        } else {
            boardLikeRepository.delete(findBoardLike.get());
        }
    }

    private void permissionCheck(User user, Board board) {
        if (!user.getId().equals(board.getCreatedBy().getId())) {
            throw new PermissionException();
        }
    }

    private void boardFileDelete(Board board) {
        List<Long> fileIdList = board.getBoardFileList().stream()
                .map(boardFile -> boardFile.getFile().getId())
                .collect(Collectors.toList());
        boardFileRepository.deleteAllByFileIds(fileIdList);
        fileIdList.forEach(fileService::deleteById);
    }
}
