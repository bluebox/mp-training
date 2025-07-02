class User():
    def __init__(self,name,age,pwd,email,activity = None, history_sections = None):
        self.name = name
        self.age = age
        self.pwd = pwd
        self.user_id = email
        self.activity = activity
        self.history_sections = history_sections

    def add_activity(self,activity):
        pass
    def add_section(self,section):
        self.history_sections.append(section)
