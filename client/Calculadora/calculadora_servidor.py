import sys
sys.path.append("gen-py")
from calculadora import CalculadoraServicio	# Importar stubs

from thrift.transport import TSocket	# Endpoint -> conectar clientes
from thrift.transport import TTransport
from thrift.protocol import TBinaryProtocol # Serialización
from thrift.server import TServer # clases de servidores

# Implementación del servicio

class CalculadoraHandler:   # Manejador de llamadas al sevicio
	def suma(self, n1, n2):
		print("sumar(%d,%d)" % (n1, n2))
		return n1 + n2

	def resta(self, n1, n2):
		print("restar(%d,%d)" % (n1, n2))
		return n1 - n2

	def multiplicacion(self, n1, n2):
		print("multiplicar(%d,%d)" % (n1, n2))
		return n1 * n2

	def division(self, n1, n2):
		print("dividir(%d,%d)" % (n1, n2))
		return n1 / n2
		
# Crear una instancia del manejador
manejador = CalculadoraHandler()

# Iniciacilizar un procesador para el servicio
procesador = CalculadoraServicio.Processor(manejador)

# Crear el socket TCP
puerto = 9090
transporte_serv = TSocket.TServerSocket(port=puerto)

# Buffer -> Eficiencia
transporte_fact = TTransport.TBufferedTransportFactory()

# Protocolo binario
protocolo_fact = TBinaryProtocol.TBinaryProtocolFactory()

# crear un servidor con funcionalidad básica
servidor = TServer.TSimpleServer(procesador, transporte_serv, transporte_fact, protocolo_fact)
print("Iniciando el servidor en el puerto %s" % puerto)
servidor.serve()