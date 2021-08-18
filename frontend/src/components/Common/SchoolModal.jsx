import { useState } from "react";
import { useRecoilState } from "recoil";
import { createPortal } from "react-dom";
import { filterState } from "../../recoil/filterState";
import * as S from "./style";

function SchoolModal(props) {
  const { message, closeModal } = props;
  const [search, setSearch] = useState(null);
  const [school, setSchool] = useRecoilState(filterState);
  const Information = [
    {
      id: 1,
      name: "고려대학교",
    },
    { id: 2, name: "연세대학교" },
    {
      id: 3,
      name: "서울대학교",
    },
    {
      id: 4,
      name: "중앙대학교",
    },
    {
      id: 5,
      name: "성균관대학교",
    },
    {
      id: 6,
      name: "성신여자대학교",
    },
    {
      id: 7,
      name: "한림대학교",
    },
  ];
  const searchSpace = (event) => {
    let keyword = event.target.value;
    setSearch(keyword);
  };
  const getSchool = async (e) => {
    console.log(e.target.attributes[0].nodeValue);
    await closeModal(e.target.textContent, e.target.attributes[0].nodeValue);
    setSchool({
      school: e.target.textContent,
      schoolId: e.target.attributes[0].nodeValue,
    });
  };
  const items = Information.filter((data) => {
    console.log(data);
    if (search == null) return data;
    else if (data.name.toLowerCase().includes(search.toLowerCase())) {
      return data;
    }
  }).map((data) => {
    return (
      <li key={data.id} style={{ position: "relative", left: "10vh" }}>
        <span onClick={getSchool} data-key={data.id}>
          {data.name}
        </span>
      </li>
    );
  });

  return createPortal(
    <>
      <S.ModalWrapper>
        <S.ModalContent>
          <S.ModalInput onChange={searchSpace} />
          <S.ModalBottom>{items}</S.ModalBottom>
        </S.ModalContent>
      </S.ModalWrapper>
    </>,
    document.getElementById("modal")
  );
}

export default SchoolModal;
