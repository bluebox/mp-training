class Membership:
    basePlan = 1000
    goldPlan = 2000
    premiumPlan = 3000

    def __init__(self, plan, months):
        self.__plan = plan
        ch = plan[0].lower()
        if ch == 'b':
            self.__amount = months * Membership.basePlan
        elif ch == 'g':
            self.__amount = months * Membership.goldPlan
        elif ch == 'p':
            self.__amount = months * Membership.premiumPlan
        else:
            raise ValueError("Invalid plan type")

    def get_amount(self):
        return self.__amount
    def display(self):
        print(self.__plan,self.__amount)
class MembershipPlan:
    pass