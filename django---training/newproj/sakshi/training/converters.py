class UsernamePathConverter:
    regex = '[A-Z,a-z]{5}'

    def to_python(self, value):
        # convert value to its corresponding python datatype
        return value

    def to_url(self, value):
        # convert the value to str data
        return value