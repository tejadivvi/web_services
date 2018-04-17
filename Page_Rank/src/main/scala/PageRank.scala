
package org.apache.spark.examples

import org.apache.spark.sql.SparkSession

object teja {

  val filePath = "textfile.txt";
  val iteration = 10;
  val DAMPING_FACTOR = 0.85;

  def main(args: Array[String]) : Unit = {
    val sparkConf = new SparkConf()
    .setAppName("PageRank")
    .setMaster("local")
    
    val sparkContext = new SparkContext(sparkConf)

    val links = sparkContext.textfile(filePath, 1).map { line =>
      val us = line.split(" ")
      (us(0), us(1))
    }.distinct().groupByKey().cache()

    var ranks = links.mapValues(link => 1.0)

    for(i <- 1 to iteration) {
      println("iteration":+i)
      val contribs = links.join(ranks).value.flatmap{case (urls, rank) =>
        val size = urls.size
        url.map(url => (url,rank/size))
      }
      ranks = contribs.reduceByKey(_ + _).mapValues { x => 0.15 + DAMPING_FACTOR * x }
      ranks.take(10).foreach(println
      }
    ranks = ranks.sortBy(_._2, false)
    ranks.saveAsTextFile("outfiles")

    }
  }
