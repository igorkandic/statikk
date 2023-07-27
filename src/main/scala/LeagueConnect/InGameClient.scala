package LeagueConnect


import java.security.KeyStore
import java.security.cert.{Certificate, CertificateFactory}
import java.net.ServerSocket
import java.net.InetSocketAddress

import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManagerFactory

import java.net.http.HttpResponse.BodyHandlers
import java.net.URI
import java.net.http.{HttpClient, HttpRequest, HttpResponse}



class InGameClient {
  private val url = "https://127.0.0.1:2999"
  private var sslContext: SSLContext = null
  def InGameClient(): Unit = {
    sslContext = createSSLContext()
  }
  def Subscribe(event: String, delay: Int, callback: (String) => Unit) : Unit = {
    val httpClient: HttpClient = HttpClient.newBuilder().sslContext(sslContext).build()
    val thread = new Thread {
      override def run(): Unit = {
        while (true) {
          println(url + event)
          if(isPortUp){
            val httpRequest: HttpRequest = HttpRequest.newBuilder(new URI(f"${url + event}")).build()
            val httpResponse: HttpResponse[String] = httpClient.send(httpRequest, BodyHandlers.ofString())
            if(httpResponse.statusCode() == 200)
            callback(httpResponse.body())
          }
          Thread.sleep(delay)
        }
      }
    }
    thread.start()
  }

  private def isPortUp: Boolean = try {
    val serverSocket = new ServerSocket()
    try {
      serverSocket.setReuseAddress(false)
      serverSocket.bind(new InetSocketAddress("127.0.0.1", 2999), 1)
      false
    } catch {
      case e =>
        true
    } finally if (serverSocket != null) serverSocket.close()
  }

  //i stole this from https://github.com/samDobsonDev/Pyke
  private def createSSLContext() = {
    val password = "nopass".toCharArray

    val is = getClass.getResourceAsStream("/riotgames.pem")

    if (is == null) {
      val errMsg = "Could not find certificate file '/riotgames.pem'"
      println(errMsg)
    }
      // Load the keystore
      val ks = KeyStore.getInstance(KeyStore.getDefaultType)
      ks.load(null, password)
      println("Keystore loaded...")
      // Create a CertificateFactory and import the certificate
      val cf = CertificateFactory.getInstance("X.509")
      val cert = cf.generateCertificate(is).asInstanceOf[Certificate]
      println("Certificate imported...")
      // Add the certificate to the keystore
      ks.setCertificateEntry("riotgames", cert)
      println("Certificate added to keystore...")
      // Create a TrustManagerFactory with the trusted store
      val tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm)
      tmf.init(ks)
      println("TrustManagerFactory initialized with the trusted keystore...")
      // Create an SSLContext with the trust manager
      val context = SSLContext.getInstance("TLS")
      context.init(null, tmf.getTrustManagers, null)
      println("Creating SSL context...")
      context

  }

}
