import styled from "styled-components";

export const FeedCard = styled.div`
    margin: 2rem;
    max-width: 40rem;
    border: 1px solid black;
`;

export const FeedInfo = styled.div`
    margin: 0.5rem;
`;

export const ButtonContainer = styled.div`

`;

export const PageButton = styled.button`

`;
export const Calendar = styled.div`

`;
export const MonthController = styled.div`

`;
export const SelectModal = styled.div`
    height: 25rem;
    width: 25rem;
    background-color:grey;
    position:absolute;
    transform: translate(-50%, 0);
    left:50%;
`;
export const ListInModal = styled.div`
    height:70%;
    width: 70%;
    position:relative;
    transform: translate(-50%, -50%);
    top:50%;
    left:50%;
    background-color: white;
    overflow-y: scroll;
`;
export const ListItem = styled.div`
    height: 30px;
    margin: 5px;
    text-align: center;
    cursor: pointer;
    border: 1px grey solid;
`;