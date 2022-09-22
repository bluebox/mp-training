class UserNameConverter:
    regex = 'MED[0-9]{4}'
    def to_python(self,value):
        return value
    def to_url(self, value):
        return value


class PanCardConverter:
    regex = "[A-Z]{5}[0-9]{4}[A-Z]{1}"
    def to_python(self,value):
        return value
    def to_url(self, value):
        return value

