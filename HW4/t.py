import resource, sys
resource.setrlimit(resource.RLIMIT_STACK, (2**29,-1))
sys.setrecursionlimit(10**6)

m = {}
def h(n):
    if(n == 1):
        return 4
    if(n == 2):
        return 14
    if(n == 3):
        return 49
    if(n in m.keys()):
        return m[n]
    m[n] = 4 * h(n - 1) - 2 * h(n - 2) + h(n - 3)
    return m[n]

if __name__ == "__main__":
    print(h(100000))