__author__ = 'Hari'

from tasks.basics import module1


notes = '''
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

    assert False == ("package1" in locals())
    assert False == ("module1" in locals())
    assert False == ("package1.module1" in locals())

    from tasks.basics import package1

    assert True == ("package1" in locals())
    assert False == ("module1" in locals())
    assert False == ("package1.module1" in locals())

    assert 'module' == type(package1).__name__

    assert False == ("package1" in sys.modules)
    assert False == ("module1" in sys.modules)
    assert False == ("package1.module1" in sys.modules)

    try:
        print (package1.module1.__doc__)
    except AttributeError :
        pass

    #modules need explicit import generally.
    #import package1.module1
    print (module1.__doc__)

    assert False == ("package1" in sys.modules)
    assert False == ("module1" in sys.modules)
    assert False == ("package1.module1" in sys.modules)


def clear_sys_modules():
    sys.modules.pop("module1", None)
    sys.modules.pop("package1", None)
    sys.modules.pop("package1.module1", None)
    sys.modules.pop("package1.subpackage", None)
    sys.modules.pop("package1.subpackage.m1", None)

def test_package_from_import():
    clear_sys_modules()

    assert False == ("package1" in locals())
    assert False == ("module1" in locals())
    assert False == ("package1.module1" in locals())

    from tasks.basics.package1 import module1

    assert False == ("package1" in locals())
    assert True == ("module1" in locals())
    assert False == ("package1.module1" in locals())

    assert False == ("package1" in sys.modules)
    assert False == ("module1" in sys.modules)
    assert False == ("package1.module1" in sys.modules)


def test_package_import_failure():
    clear_sys_modules()
    try:
        import package2
    except ImportError :
        assert UnboundLocalError

    # fill up reason for failure. why is package2 not a package
    why_it_failed = "package2 called before import"

def test_package_sub_packages():
    clear_sys_modules()

    assert False == ("package1" in locals())
    assert False == ("subpackage" in locals())
    assert False == ("package1.subpackage" in locals())

    from tasks.basics.package1 import subpackage

    assert False == ("package1" in locals())
    assert True == ("subpackage" in locals())
    assert False == ("package1.subpackage" in locals())

    assert False == ("package1" in sys.modules)
    assert False == ("module1" in sys.modules)
    assert False == ("package1.module1" in sys.modules)
    assert False == ("package1.subpackage" in sys.modules)
    assert False == ("package1.subpackage.m1" in sys.modules)

    #why is this not raising an exception here?
    print(subpackage.m1.__doc__)

    assert False == ("package1.subpackage.m1" in sys.modules)

three_things_i_learnt = """
- The relationship between packages and modules is similar to that of directories and files in the
 filesystem.
- Packages can contain sub-packages and modules.
- A module with name a.b.c is saying that c is a module in package b which is a sub-package of module a.
"""

time_taken_minutes = 15