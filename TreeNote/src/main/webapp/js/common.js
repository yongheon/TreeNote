/**
 * 특수문자 전체제거
 * @param obj
 * @param chr
 * @returns 
 * @create by 임선애 2014.09.18
 */
function js_RemoveAll(obj) {
    for (i=0; i< obj.value.length; i++) 
        obj.value = obj.value.replace(/(\;|\"|\=|\~|\&|\%|\!|\_|\#|\,|\'|\`|\/|\$|\^|\*|\(|\)|\+|\.|\?|\\|\{|\}|\||\[|\]|\-|\:|\<|\>|\@)/g,"");
    return obj.value;
}

/**
 * 특정문자 제거
 * @param obj
 * @param chr
 * @returns 
 * @create by 임선애 2014.09.18
 */
function js_Remove(obj, chr) {
    for (i=0; i< obj.value.length; i++) 
        obj.value = obj.value.replace(chr,"");
    return obj.value;
}