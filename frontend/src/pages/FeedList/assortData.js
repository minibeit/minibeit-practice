export function assortData(data, category){
    let arr = []
    arr = data.filter(ele => ele.category == `${category}`)
    return arr
}