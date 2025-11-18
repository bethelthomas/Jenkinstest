FROM python:3.11-slim
WORKDIR /app
COPY docker/requirements.txt /app/requirements.txt
RUN pip install --no-cache-dir -r /app/requirements.txt
COPY app /app
ENV FLASK_RUN_HOST=0.0.0.0
EXPOSE 5000
CMD ["python", "app.py"]
