package com.iservport.xml

import java.io.File
import java.nio.file.{Path, Paths}

import akka.actor.{ActorSystem, Props}
import akka.stream.ActorMaterializer
import akka.util.Timeout
import com.iservport.xml.actor.FileActor

import scala.concurrent.duration._

/**
  * Created by mauriciofernandesdecastro on 29/05/17.
  */
object Application extends App {

  implicit val system = ActorSystem("Test")
  implicit val executor = system.dispatcher
  implicit val mat = ActorMaterializer()
  implicit val timeout = Timeout(3.seconds)

  lazy val fileActor = system.actorOf(Props(new FileActor()) , "_file")

  val fName = "/Users/mauriciofernandesdecastro/workspace/iservport-alpakka-xml/src/main/resources/"

  fileActor ! new File(fName)

}
