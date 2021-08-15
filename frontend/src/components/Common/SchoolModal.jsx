import { useState } from "react";
import { createPortal } from "react-dom";
import * as S from "./style";

function SchoolModal(props) {
  const { message, closeModal } = props;
  const [search, setSearch] = useState(null);
  const Information = [
    {
      id: 1,
      name: "Samule",
    },
    { id: 2, name: "Sam" },
    {
      id: 3,
      name: "Mark",
    },
    {
      id: 4,
      name: "Markus",
    },
  ];
  const searchSpace = (event) => {
    let keyword = event.target.value;
    setSearch(keyword);
  };
  const getSchool = async (event) => {
    console.log(event.target.textContent);
    await closeModal(event.target.textContent);
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
        <span onClick={getSchool}>{data.name}</span>
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
