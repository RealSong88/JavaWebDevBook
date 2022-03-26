package groovy

str1 = 'july2day '
str2 = "test is $str1"
str3 = str1 + str2

println str1 + " " + str2 + " " + str3

name = 'song'
println "hi, $name."
println "hi, \$name."

assert str1 instanceof String
assert str2 instanceof GString
assert str3 instanceof String

now = new Date()
str1 = "$now"
str2 = "${new Date()}"
str3 =  "${writer -> writer << new Date()}"

println str1
println str2
println str3