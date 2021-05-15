# BDSD-Assignment2
 list<Row> tekst = [Row, Row, Row, Row, Row]
list<Pair> zin = [{key: a b, value: 2}]

checkPairsDutch = [{key: a b, value: 0.5}, ....]
checkPairsEnglish = [{key: a b, value: 0.2}, ....]

for(Row zin : tekst) {
	for(Pair pair: zin.getRows()) {
		checkPairDutch = checkPairsDutch.whereKey(pair.key)
		checkPairEnglish = checkPairsEnglish.whereKey(pair.key)
		dutchChance = checkPairDutch.value * pair
		englishChance = checkPairEnglish.value * pair
		if(dutchChance > englishChance) {
			pair.language = 'Dutch'
		} else {
			pair.language = 'English'
		}
	}
}
Model {
	Matrix input
	Matrix engels
	Matrix nederlands
}

Matrix {
	List<Row> rows
	String label (Nederlands of Engels)
}


Pair {
	key
	value
	language (optional)
     }
     
Row {
	List<Pair> pairs
	String detectedLanguage
	List<Pair> getRows() {
		return this.rows;
		}
    }
   
Tekst {
	List<Row> rows
	
	getDutch () {
		.... tellen hoeveel rows Nederlands
	}
	getEnglish () {
		.... tellen hoeveel rows Engels
	}
}


rababdbasbd = NL
asdasdasdasd = ENG
rababdbasbd = NL
asdasdasdasd = ENG
rababdbasbd = NL
asdasdasdasd = ENG
rababdbasbd = NL
asdasdasdasd = ENG
rababdbasbd = NL
asdasdasdasd = ENG
rababdbasbd = NL
asdasdasdasd = ENG
rababdbasbd = NL
asdasdasdasd = ENG
rababdbasbd = NL
asdasdasdasd = ENG
rababdbasbd = NL
asdasdasdasd = ENG

NL: 22
ENG: 44


