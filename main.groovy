import java.io.File 
import static groovy.io.FileType.FILES

class  Game {

    def  date
    def  numbers = []
    def  complementary 
    def  returned

    Game( String date,String complementary ,String returned  ,String... numbers){
        this.date = date
        this.numbers = numbers.collect{it -> it.trim() as Integer}
        this.complementary = complementary  as Integer
        this.returned = returned as Integer
    }

    public String  toString(){
        return  "date: ${date},complementary: ${complementary} ,returned: ${returned}  ,numbers: ${numbers}"
    }

    static Game build(String params){
        def  containsSplited = params.split(";")
        if ( params.contains(";") && containsSplited.length > 5 ){
            return new Game(containsSplited[0],containsSplited[7],containsSplited[8], containsSplited[1], containsSplited[2], containsSplited[3], containsSplited[4], containsSplited[5], containsSplited[6])
        } else 
           return null
    }
}




def extract(size, numbers){
    def comb
    comb = { m, list ->
        def n = list.size()
        m == 0 ?
            [[]] :
            (0..(n-m)).inject([]) { newlist, k ->
                def sublist = (k+1 == n) ? [] : list[(k+1)..<n] 
                newlist += comb(m-1, sublist).collect { [list[k]] + it }
            }
    }

    def combinations = (0..(numbers.size())).collect { i ->  comb(i, numbers) }
    def combinationsByLength =  combinations.collectEntries( item -> [item.get(0).size(), item])
    return  combinationsByLength[size]
}


def loadContent(){
    def lstGames= []
    def dir = new File("./csv")
    dir.eachFileRecurse(FILES) {  file ->
        def lstContent = new File(file.path).collect {it}
        lstGames += lstContent.withIndex().collect { it, index -> 
            if (index >= 8){
                Game.build(it)}
            else{
                return null }
        }
    }
    return lstGames
}

def evaluate(){
    def filteredLoadedNumbers = loadContent().findAll{it -> it != null}
    def extracted4Size = filteredLoadedNumbers.collect ( it ->  extract(4, it.numbers))
    println extracted4Size.size()
    extracted4Size.collect{it -> it.collect{ sub-it -> }}
    //def listOfPermutations =[]
    //filteredLoadedNumbers.each { it ->
    //    listOfPermutations += it.numbers.permutations()
    //}
}

evaluate()
// 
// def csny = [1,4,7,9,30,45]
// 
// 
// 
// 
// println extract(4, csny)