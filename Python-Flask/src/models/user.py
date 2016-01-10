from src.common.database import Database


class User(object):
    def __init__(self, username, password):
        self.username = username
        self.password = password

    def json(self):
        return {
            "username": self.username,
            "password": self.password
        }

    def save(self):
        Database.insert("users", self.json())
