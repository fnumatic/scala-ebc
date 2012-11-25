organization := "de.fnumatic"

name := "scala-ebc"

version := "0.1"

scalaVersion := "2.10.0-RC2"

scalacOptions += "-deprecation"

autoCompilerPlugins := true

libraryDependencies <<= (scalaVersion, libraryDependencies) { (ver, deps) =>
    deps :+ compilerPlugin("org.scala-lang.plugins" % "continuations" % ver)
}

scalacOptions += "-P:continuations:enable"

libraryDependencies += "cc.co.scala-reactive" %% "reactive-core" % "0.2"


// test Libraries
libraryDependencies += "org.scalatest" %% "scalatest" % "2.0.M5" % "test" cross CrossVersion.full

libraryDependencies += "junit" % "junit" % "4.8" % "test"
