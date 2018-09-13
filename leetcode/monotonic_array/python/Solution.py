class Solution(object):
    def isMonotonic(self, A):
        """
        :type A: List[int]
        :rtype: bool
        """
        increasing = True
        decreasing = True
        
        # base case
        if (len(A) == 0):
            return True
        
        prev = A[0]
        for i in range(1, len(A)):
            increasing = increasing and (prev <= A[i])
            decreasing = decreasing and (prev >= A[i])
            prev = A[i]
        return increasing or decreasing
