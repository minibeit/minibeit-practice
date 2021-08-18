import { atom } from "recoil"
import { persistAtom } from "./common"

export const filterState = atom({
    key: 'filterState',
    default: {
        school: null,
        schoolId: null,
    },
    effects_UNSTABLE: [persistAtom],
})