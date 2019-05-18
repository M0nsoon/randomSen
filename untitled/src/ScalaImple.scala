import scala.collection.mutable
import scala.io.Source
import scala.util.Random
class ScalaImple {

    val fileName = "MyText.txt"
    //load
    val Store = Source.fromFile(fileName).getLines()
    var work = Store.flatMap(_.split(" ")).toArray

    // work.foreach(println) make sure it works
    //update array
    for (i <- 0 to work.length - 1) {
      if ((work(i).reverse(0) == '.')) {
        val temp = work.slice(0, i)
        val temp2 = work.slice(i + 1, work.length - 1)
        work = temp.:+(".").:+("") ++ temp2
        //add 2 element "." & "" right after the end of the sentence
      }
    }

    //mapping each word
    val hm = new mutable.HashMap[String, List[String]]()

    def collectSuccessor(str: String): Unit = {
      val lm: List[String] = List()
      for (i <- 0 to work.length - 1) {
        //add found elements to list
        if (work(i) == str) lm.:+(work(i + 1))
      }
      hm.put(str, lm)
    }

    val mapList = work.foreach(collectSuccessor(_))
    //pick out random member from lists
  def pickOutFollowingWord(str: String): String ={
    val random = new Random()
    val res1 = hm.get(str)
    res1(random.nextInt(res1.size))
  }

  def generate(str: String):String ={
    
    val arr = str.split(" ")
    var res = ""
    for(i<- 0 to arr.length-1) {
      res += pickOutFollowingWord(arr(i))
    }
    res
  }

}
