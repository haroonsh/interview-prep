class Solution(object):
    def orderlyQueue(self, S, K):
        """
        :type S: str
        :type K: int
        :rtype: str
        """
        best = S
        if (K == 1):
            # consider only rotations
            for i in range(0, len(S)):
                temp = S[i:] + S[:i]
                if (temp < best):
                    best = temp
            return best
        else:
            # sort string lexigraphically
            return "".join(sorted(S))
