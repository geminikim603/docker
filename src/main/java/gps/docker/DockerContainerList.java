package gps.docker;

import com.spotify.docker.client.DefaultDockerClient;
import com.spotify.docker.client.DockerClient;
import com.spotify.docker.client.messages.Container;
import java.util.List;

public class DockerContainerList {

  // usage
  // sudo java -cp ./docker-ms-demo-1.0 gps.docker.DockerContainerList

  public static void main(String[] args) throws Exception {
    DockerClient docker = DefaultDockerClient.fromEnv().build();
    List<Container> containers = docker.listContainers(DockerClient.ListContainersParam.allContainers());

    System.out.println("\n-----------------------------------------------------------------------------");
    System.out.println("CONTAINER-ID                 "
        + "CONTAINER-STATUS            "
        + "CONTAINER-IMAGE                "
    );
    System.out.println("-----------------------------------------------------------------------------");
    for (Container container : containers) {
      System.out.println(container.id().substring(0, Math.min(30, container.id().length()))
          + " " + container.status() +
          "\t " + container.image().substring(0, Math.min(60, container.image().length()))
      );
    }
    docker.close();
  }
}
