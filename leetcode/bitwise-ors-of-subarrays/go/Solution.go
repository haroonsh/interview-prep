func subarrayBitwiseORs(A []int) int {
    ans := make(map[int]bool)
    curr := make(map[int]bool)
    curr[0] = true
    
    for _, element := range A {
        newCurr := make(map[int]bool)
        for k := range curr {
            newCurr[k | element] = true
        }
        newCurr[element] = true
        curr = newCurr
        for k := range curr {
            ans[k] = true
        }
    }
    return len(ans);
}
