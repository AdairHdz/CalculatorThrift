var CalculatorService = require("./gen-nodejs/CalculatorService");
const thrift = require("thrift")

var server = thrift.createServer(CalculatorService, {
  sum: function(a, b, result) {
    result(null, a + b);
  },
  substract: function(a, b, result){
    result(null, a - b)
  },
  multiply: function(a, b, result){
      result(null, a * b)
  },
  divide: function(a, b, result){
      result(null, a/b)
  }
});
console.log("Server listening in port " + 9090);
server.listen(9090);