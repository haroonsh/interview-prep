/**
 * @param {number[]} A
 * @return {number}
 */
var subarrayBitwiseORs = function(A) {
    let ans = new Set();
    let curr = new Set();
    
    curr.add(0)
    for (var i in A) {
        let element = A[i];
        let newCurr = new Set();
        for (let val of curr.keys()) {
            newCurr.add(element | val);
        }
        newCurr.add(element);
        curr = newCurr;
        curr.forEach(ans.add, ans);
    }
    return ans.size;
};
