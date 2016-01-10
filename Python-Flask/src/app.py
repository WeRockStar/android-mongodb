from flask import Flask, request

from src.common.database import Database
from src.models.user import User

app = Flask(__name__)

@app.before_first_request
def initial_database():
    Database.initialize()

@app.route("/", methods=['POST'])
def save():
    if request.method == 'POST':
        username = request.form['username']
        password = request.form['password']
        user = User(username, password)
        user.save()
    return

if __name__ == "__main__":
    app.run()
