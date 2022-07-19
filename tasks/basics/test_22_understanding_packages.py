<<<<<<< HEAD
import sys
#from tasks.placeholders import *
=======
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa
__author__ = 'Hari'

from tasks.basics import module1
from tasks.basics.package1.subpackage import m1

NOTES = '''
 Sometimes a collection of modules provides related functionality as part of a larger framework,
 then it makes sense to group all of them together. Packages allows you to group related modules together.

 The relationship between packages and modules is similar to that of directories and files in the
 filesystem. Packages can contain sub-packages and modules. In the filesystem a directory containing __init__.py
 is treated as a package when python tries to find packages on sys.path.

 A module with name a.b.c is saying that c is a module in package b which is a sub-package of module a.
'''

from tasks.placeholders import *
import sys

# Look at the package1 and package2 directories before starting...

def test_package_basic_import():
    clear_sys_modules()

<<<<<<< HEAD
    assert False is ("package1" in locals())
    assert False is ("module1" in locals())
    assert False is ("package1.module1" in locals())

    from tasks.basics import package1

    assert True is ("package1" in locals())
    assert False is ("module1" in locals())
    assert False is ("package1.module1" in locals())
=======
    assert __ == ("package1" in locals())
    assert __ == ("module1" in locals())
    assert __ == ("package1.module1" in locals())

    import package1

    assert __ == ("package1" in locals())
    assert __ == ("module1" in locals())
    assert __ == ("package1.module1" in locals())
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa

    assert __ == type(package1).__name__

<<<<<<< HEAD
    assert False is ("package1" in sys.modules)
    assert False is ("module1" in sys.modules)
    assert False is ("package1.module1" in sys.modules)
=======
    assert __ == ("package1" in sys.modules)
    assert __ == ("module1" in sys.modules)
    assert __ == ("package1.module1" in sys.modules)
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa

    try:
        print(module1.__doc__)
    except __ :
        pass

<<<<<<< HEAD
    # modules need explicit import generally.
    import tasks.basics.package1.module1
    print(module1.__doc__)

    assert False is ("package1" in sys.modules)
    assert False is ("module1" in sys.modules)
    assert False is ("package1.module1" in sys.modules)
=======
    #modules need explicit import generally.
    import package1.module1
    print(module1.__doc__)

    assert __ == ("package1" in sys.modules)
    assert __ == ("module1" in sys.modules)
    assert __ == ("package1.module1" in sys.modules)
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa


def clear_sys_modules():
    sys.modules.pop("module1", None)
    sys.modules.pop("package1", None)
    sys.modules.pop("package1.module1", None)
    sys.modules.pop("package1.subpackage", None)
    sys.modules.pop("package1.subpackage.m1", None)

def test_package_from_import():
    clear_sys_modules()

<<<<<<< HEAD
    assert False is ("package1" in locals())
    assert False is ("module1" in locals())
    assert False is ("package1.module1" in locals())

    from tasks.basics.package1 import module1

    assert False is ("package1" in locals())
    assert True is ("module1" in locals())
    assert False is ("package1.module1" in locals())

    assert False is ("package1" in sys.modules)
    assert False is ("module1" in sys.modules)
    assert False is ("package1.module1" in sys.modules)
=======
    assert __ == ("package1" in locals())
    assert __ == ("module1" in locals())
    assert __ == ("package1.module1" in locals())

    from package1 import module1

    assert __ == ("package1" in locals())
    assert __ == ("module1" in locals())
    assert __ == ("package1.module1" in locals())

    assert __ == ("package1" in sys.modules)
    assert __ == ("module1" in sys.modules)
    assert __ == ("package1.module1" in sys.modules)
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa


def test_package_import_failure():
    clear_sys_modules()
    try:
        import package2
<<<<<<< HEAD

    except ModuleNotFoundError:
        assert True

    # fill up reason for failure. why is package2 not a package
    #WHY_IT_FAILED = """
    #package2 doesn't have the __init__.py file.
    #Every python package should contain init file"""

=======
    except __ :
        assert ___

    # fill up reason for failure. why is package2 not a package
    why_it_failed = __
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa

def test_package_sub_packages():
    clear_sys_modules()

<<<<<<< HEAD
    assert False is ("package1" in locals())
    assert False is ("subpackage" in locals())
    assert False is ("package1.subpackage" in locals())

    from tasks.basics.package1 import subpackage

    assert False is ("package1" in locals())
    assert True is ("subpackage" in locals())
    assert False is ("package1.subpackage" in locals())

    assert False is ("package1" in sys.modules)
    assert False is ("module1" in sys.modules)
    assert False is ("package1.module1" in sys.modules)
    assert False is ("package1.subpackage" in sys.modules)
    assert False is ("package1.subpackage.m1" in sys.modules)
=======
    assert __ == ("package1" in locals())
    assert __ == ("subpackage" in locals())
    assert __ == ("package1.subpackage" in locals())

    from package1 import subpackage

    assert __ == ("package1" in locals())
    assert __ == ("subpackage" in locals())
    assert __ == ("package1.subpackage" in locals())

    assert __ == ("package1" in sys.modules)
    assert __ == ("module1" in sys.modules)
    assert __ == ("package1.module1" in sys.modules)
    assert __ == ("package1.subpackage" in sys.modules)
    assert __ == ("package1.subpackage.m1" in sys.modules)
>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa

    #why is this not raising an exception here?
    print(m1.__doc__)

    assert __ == ("package1.subpackage.m1" in sys.modules)

<<<<<<< HEAD
THREE_THINGS_I_LEARNT = """
how to use packages
what packages contain
-
"""

TIME_TAKEN_MINUTES = 120
=======
three_things_i_learnt = """
-
-
-
"""

time_taken_minutes = ___


>>>>>>> 8849978d1e574815f3c22a9f3deb186f39a28aaa
