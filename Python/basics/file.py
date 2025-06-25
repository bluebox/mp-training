from Gym.MembershipPlan import Membership
from Gym.Gym import Gym
from Gym.Member import Member


class filenotfound(Exception):
    def __init__(self, msg):
        super().__init__(msg)

#
# with open("test.txt", 'w') as f:
#     f.write("ram")b

try:
    with open("test.txt", 'r') as f:
        content = f.read()
        print(content)
except Exception as e:
    try:
        raise filenotfound("File is missing")
    except Exception as e:
        print(e)
else:
    print("reading the file")

a = Gym()
a.addMember(Member("anand", 22), Membership("Basic", 10))
a.display()
