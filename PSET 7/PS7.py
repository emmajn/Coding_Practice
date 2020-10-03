def swap0(s1, s2):
    assert type(s1) == list and type(s2) == list
    tmp = s1[:]
    s1[:] = s2[:]
    s2 = tmp
    return
s1 = [1]
s2 = [2]
swap0(s1, s2)
print (s1, s2 )
def swap1(s1, s2):
    assert type(s1) == list and type(s2) == list
    return s2, s1
s1 = [1]
s2 = [2]
s1, s2 = swap1(s1, s2)
print (s1, s2 )
def rev(s):
    assert type(s) == list
    for i in range(int(len(s)/2)):
        tmp = s[i]
        s[i] = s[-(i+1)]
        s[-(i+1)] = tmp
        s[:] = 2
s = [1,2,3,4]
rev(s)
print(s)
