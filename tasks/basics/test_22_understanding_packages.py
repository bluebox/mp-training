"""MODULE TO UNDERSTAND PACKAGES"""
__author__ = 'Hari'

from tasks.basics import module1
from tasks.basics.package1.subpackage import m1
from tasks.placeholders import *
import sys

NOTES = '''
 Sometimes a collection of modules provides related functionality as part of a larger framework,
 then it makes sense to group all of them together. Packages allows you to group related modules together.

 The relationship between packages and modules is similar to that of directories and files in the
 filesystem. Packages can contain sub-packages and modules. In the filesystem a directory containing __init__.py
 is treated as a package when python tries to find packages on sys.path.

 A module with name a.b.c is saying that c is a module in package b which is a sub-package of module a.
'''


# Look at the package1 and package2 directories before starting...

def test_package_basic_import():
    """test package basic import"""
    clear_sys_modules()
    assert False is ("package1" in locals())
    assert False is ("module1" in locals())
    assert False is ("package1.module1" in locals())

    from tasks.basics import package1
    print(type(package1).__name__)
    assert True is ("package1" in locals())
    assert False is ("module1" in locals())
    assert False is ("package1.module1" in locals())

    assert 'module' == type(package1).__name__

    assert False is ("package1" in sys.modules)
    assert False is ("module1" in sys.modules)
    assert False is ("package1.module1" in sys.modules)

    try:
        print(module1.__doc__)
    except AttributeError:
        pass

    # modules need explicit import generally.
    import package1.module1
    print(module1.__doc__)

    assert True is ("package1" in sys.modules)
    assert False is ("module1" in sys.modules)
    assert True is ("package1.module1" in sys.modules)


def clear_sys_modules():
    """clear system modules"""
    sys.modules.pop("module1", None)
    sys.modules.pop("package1", None)
    sys.modules.pop("package1.module1", None)
    sys.modules.pop("package1.subpackage", None)
    sys.modules.pop("package1.subpackage.m1", None)


def test_package_from_import():
    """test package from import"""
    clear_sys_modules()

    assert False is ("package1" in locals())
    assert False is ("module1" in locals())
    assert False is ("package1.module1" in locals())

    from package1 import module1

    assert False is ("package1" in locals())
    assert True is ("module1" in locals())
    assert False is ("package1.module1" in locals())

    assert True is ("package1" in sys.modules)
    assert False is ("module1" in sys.modules)
    assert True is ("package1.module1" in sys.modules)


def test_package_import_failure():
    """test package import failure"""
    clear_sys_modules()
    try:
        import package2
    except ModuleNotFoundError:
        assert True

    # fill up reason for failure. why is package2 not a package
    # why_it_failed = clear_sys_modules() function pop out modules from the current module


def test_package_sub_packages():
    """test package sub-packages"""
    clear_sys_modules()

    assert False is ("package1" in locals())
    assert False is ("subpackage" in locals())
    assert False is ("package1.subpackage" in locals())

    from package1 import subpackage

    assert False is ("package1" in locals())
    assert True is ("subpackage" in locals())
    assert False is ("package1.subpackage" in locals())

    assert True is ("package1" in sys.modules)
    assert False is ("module1" in sys.modules)
    assert False is ("package1.module1" in sys.modules)
    assert True is ("package1.subpackage" in sys.modules)
    assert False is ("package1.subpackage.m1" in sys.modules)

    # why is this not raising an exception here?
    print(m1.__doc__)

    assert False == ("package1.subpackage.m1" in sys.modules)


THREE_THINGS_I_LEARNT = """
-module
-sysmodules
-local module
"""

TIME_TAKEN_MINUTES = 10
