package com.iservport.xml.actor

import java.io.File
import java.nio.charset.StandardCharsets

import akka.actor.{Actor, ActorLogging, Props}
import akka.stream.ActorMaterializer
import akka.stream.alpakka.xml.scaladsl.XmlParsing
import akka.stream.scaladsl.{FileIO, Flow, Keep, Sink}
import akka.util.ByteString

/**
  * Created by mauriciofernandesdecastro on 02/06/17.
  */
class FileActor extends Actor with ActorLogging {

  implicit val executor = context.dispatcher
  implicit val mat = ActorMaterializer()
  lazy val eventActor = context.actorOf(Props(new EventActor()) , "_event")

  override def receive: Receive = {
    case file: File if file.isDirectory =>
      log.info(s"\n-- Reading directory: $file")
      file.listFiles(_.getName.endsWith(".xml")).foreach(self ! _)
    case file: File =>
      log.info(s"\n-- Reading file: $file")
      FileIO.fromPath(file.toPath, 8192, 2).runWith(parse).map(elem => eventActor ! elem)
  }

  val parse = Flow[ByteString]
    .map(_.decodeString(StandardCharsets.UTF_16LE))
    .map(ByteString(_))
    .via(XmlParsing.parser)
    .toMat(Sink.seq)(Keep.right)

}
