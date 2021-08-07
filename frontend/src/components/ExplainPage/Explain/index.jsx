import React from "react";

import { PApplyExplain, PRecruitExplain } from "./PExplain";

export default function Explain(props){
    if(props.category == 'recurit'){
        return <PRecruitExplain/>;
    }else{
        return <PApplyExplain/>;
    }
}