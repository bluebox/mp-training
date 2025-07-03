def outer():
    x = "hello"
    def sample():
        def center():
            # print(x)
            def inner():
                nonlocal x
                print(x)
            inner()
        center()

outer()