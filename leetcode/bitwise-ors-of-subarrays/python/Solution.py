class Solution(object):
    def subarrayBitwiseORs(self, A):
        """
        :type A: List[int]
        :rtype: int
        """
        ans = {}
        curr = {}
        curr[0] = 0
        for element in A:
            newCurr = {}
            for val in curr:
                newCurr[val | element] = val | element
            newCurr[element] = element
            curr = newCurr
            for val in newCurr:
                ans[val] = val
        return len(ans)
                
