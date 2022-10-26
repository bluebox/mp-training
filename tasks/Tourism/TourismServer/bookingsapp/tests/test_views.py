from .tests import TestSetUP

class TestViews(TestSetUP):
        def test_user_cannot_register_without_data(self):
                res = self.client.post(self.register_url)
                import pdb
                pdb.set_trace()
                self.assertEqual(res.status_code, 400)