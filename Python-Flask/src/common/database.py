import pymongo


class Database(object):
    URL = "mongodb://127.0.0.1:27017"
    DATABASE = None

    @staticmethod
    def initialize():
        client = pymongo.MongoClient(Database.URL)
        Database.DATABASE = client['articles']

    @staticmethod
    def insert(collection, data):
        Database.DATABASE[collection].insert(data)