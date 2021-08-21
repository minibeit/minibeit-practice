import { atom } from "recoil"

export const dateFilterState = atom({
    key: 'dateFilterState',
    default: new Date()
})
export const schoolFilterState = atom({
    key: 'schoolFilterState',
    default: {
        school_name: '',
        school_id: null
    }
})