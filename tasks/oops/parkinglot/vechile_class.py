class vehicle:
    def __init__(self,name,number) :
      
        self.name=name
        self.number=number
        self.v_entry_time=None
        self.v_exit_time=None

    def entry_time(self):
        self.v_entry_time=datetime.datetime.now()
        
    def exit_time(self):
        self.v_exit_time=datetime.datetime.now() - self.v_entry_time   
