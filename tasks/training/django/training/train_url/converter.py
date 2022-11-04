class StudentUsnConverter:
     # condition : USN given by university:
     # <college code 1-9><college code A-Z/ a-z :2 characters ><joining year code 2 digit number: 1-9><branch code :A-Z/ a-z :2 characters ><3 digit roll number>  
     # EXAMPLE : 1SK18CS001 / 1sk18cs001

    regex ='[1-9]{1}[a-z,A-Z]{2}[1-9]{2}[a-z,A-Z]{2}[0-9]{3}' 

    def to_python(self,value):
        return value
    def to_url(self,value):
        return value