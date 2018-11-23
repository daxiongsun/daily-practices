"""
算法1：贪心算法
"""
"""
题目1：给定总金额N元，求最少有多少张人民币可换取N元。[100,50,20,10,5,2,1]
时间复杂度：7 * N/100

1 2 5 7 10 20 50 100
1 1
2 1 + 1
5 2 + 2 + 1
7 5 + 2 + 1
10 5 + 5
20 10 + 10
50 20 + 20 + 10
100 50 + 50
"""
class Solution:
    def findLess(self, N):
        m = [100,50,20,10,5,2,1]
        mLen = len(m)
        count = 0
        cm = []
        for i in range(mLen):
            use = int(N / m[i])
            for j in range(use):
                cm.append(m[i])
            count += use
            N = N - (use * m[i])
        print('count:', count, 'cm:', cm)
solution = Solution()
solution.findLess(123)

"""
题目2：

g = 5 10 2 9 15 9
s = 6 1 20 3 8

g = 15 10 9 9 5 2
s = 20 8 6 3 1
s = 1 3 6 8 20
1 2
3 2x
6 5
8 5x
20 15 
"""