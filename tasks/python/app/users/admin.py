from user import User
class Admin(User):
    def __init__(self,name,age,pwd,email,activity = None, history_sections = None):
        super().__init__(name,age,pwd,email,activity,history_sections)
