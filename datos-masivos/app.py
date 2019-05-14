import json

from flask_cors import CORS

from flask import Flask, request, Response
from flask_sqlalchemy import SQLAlchemy
from mixer.backend.flask import mixer
import threading

from math import ceil

app = Flask(__name__)
CORS(app)
app.config['SQLALCHEMY_DATABASE_URI'] = 'mysql+pymysql://{usuario}:{clave}@{host}/{bd}'.format(
    usuario='root',
    clave='jUVqYRPu5Hk7HmwT',
    host='0.0.0.0',
    bd='transmilenio'
)
app.config['SQLALCHEMY_POOL_SIZE'] = 100
db = SQLAlchemy(app)
mixer.init_app(app)

class Operador(db.Model):
    idOperador = db.Column(db.Integer, primary_key=True)
    nombre = db.Column(db.String(80), nullable=False)
    telefono = db.Column(db.String(80), nullable=False)
    direccion = db.Column(db.String(80), nullable=False)
    representante = db.Column(db.String(80), nullable=False)
    pagina_web = db.Column(db.String(80), nullable=False)

    def fake(self):
        self.nombre = mixer.faker.name()
        self.telefono = mixer.faker.phone_number()
        self.direccion = mixer.faker.address()
        self.representante = mixer.faker.name()
        self.pagina_web = mixer.faker.url()

    def json(self):
        return {
            'idOperador': self.idOperador,
            'nombre': self.nombre,
            'telefono': self.telefono,
            'direccion': self.direccion,
            'representante': self.representante,
            'pagina_web': self.pagina_web,
        }


class TipoEstacion(db.Model):
    idTipoEsta = db.Column(db.Integer, primary_key=True)
    nombre = db.Column(db.String(30), nullable=False)

    def fake(self):
        self.nombre = mixer.faker.street_name()

    def json(self):
        return {
            'idTipoEsta': self.idTipoEsta,
            'nombre': self.nombre,
        }

MAX_REGISTROS_POR_HILO = 1000

def generar_operadores(n, x):
    for i in range(0, n):
        operador = Operador()
        operador.fake()
        db.session.add(operador)
    db.session.commit()
    print('Fin hilo', x)

def generar_tipo_estacion(n, x):
    for i in range(0, n):
        tipo_estacion = TipoEstacion()
        tipo_estacion.fake()
        db.session.add(tipo_estacion)
    db.session.commit()
    print('Fin hilo', x)

def hilos_generar_registros(metodo, numero_registros):
    max_hilos = int(ceil(numero_registros / MAX_REGISTROS_POR_HILO))
    registros_por_hilo = int(numero_registros / max_hilos)
    registros_restantes = int(numero_registros % max_hilos)
    num_hilos = numero_registros if numero_registros < max_hilos else max_hilos
    for i in range(0, num_hilos):
        threading.Thread(target=metodo, args=(registros_por_hilo, i)).start()
    threading.Thread(target=metodo, args=(registros_restantes, -1)).start()

TABLAS = {
    'operador': generar_operadores,
    'tipo-estacion': generar_tipo_estacion,
}

@app.route('/masivo/generar-registros/', methods=['POST'])
def generar_registros():
    data = json.loads(request.data)
    for tabla in TABLAS:
        if tabla in data:
            hilos_generar_registros(TABLAS[tabla], float(data[tabla]))
    return Response("{}", status=202, mimetype='application/json')

@app.route('/masivo/operador/', methods=['GET'])
def operador_view():
    pagina = request.args.get('pagina', None)
    queryset = Operador.query.all()
    if pagina is not None:
        pagina = int(pagina)
        queryset = queryset[100*(pagina-1):100*pagina]
    rs = json.dumps([(dict(row.json())) for row in queryset])
    return Response(json.dumps(rs), status=202, mimetype='application/json')


@app.route('/masivo/tipo-estacion/', methods=['GET'])
def tipo_estacion_view():
    pagina = request.args.get('pagina', None)
    queryset = TipoEstacion.query.all()
    if pagina is not None:
        pagina = int(pagina)
        queryset = queryset[100*(pagina-1):100*pagina]
    rs = json.dumps([(dict(row.json())) for row in queryset])
    return Response(json.dumps(rs), status=202, mimetype='application/json')


@app.route('/masivo/operador/count', methods=['GET'])
def operador_count_view():
    queryset = Operador.query.count()
    rs = {'count': queryset}  
    return Response(json.dumps(rs), status=200, mimetype='application/json')
    
@app.route('/masivo/tipo-estacion/count', methods=['GET'])
def tipo_estacion_count_view():
    queryset = TipoEstacion.query.count()
    rs = {'count': queryset}
    return Response(json.dumps(rs), status=200, mimetype='application/json')

if __name__ == "__main__":
    db.create_all()
    app.run(debug=True)
