package groovy

a = 20
println a
println plus(10, 20)

def plus(x, y) {
	x + y
}


map = ['kor' : 100, 'eng' : 90]

//for(e in map) {
//	println e.key + " = " + e.value
//}

map.forEach({key, value -> println key + "=" + value })

class Test2{
	void test() {
		println "test 123456789"
	}
}

t = new Test2()
t.test()

println (/I like "groovy" ./)