from flask import Flask
import os

app = Flask(__name__)

@app.route("/")
def hello():
    version = os.getenv("VERSION", "0.1.0")
    return f"Hello from fast-jenkins-demo v{version}!"

if __name__ == "__main__":
    app.run(host="0.0.0.0", port=5000)
