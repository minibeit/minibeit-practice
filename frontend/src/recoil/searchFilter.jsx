import { atom } from "recoil"

export const schoolFilterState = atom({
    key: 'schoolFilterState',
    default: {
        name: ''
    }
})