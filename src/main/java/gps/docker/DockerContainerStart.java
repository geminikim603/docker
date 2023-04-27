package gps.docker;

import com.spotify.docker.client.DefaultDockerClient;
import com.spotify.docker.client.DockerClient;
import com.spotify.docker.client.messages.ContainerInfo;
import com.spotify.docker.client.messages.ContainerState;
import java.util.Scanner;

public class DockerContainerStart {
  public static void main(String[] args) throws Exception {

    System.out.print("===============================================================");
    System.out.print("컨테이너 이름을 입력받아 상태를 조회후, 중지상태이면 시작하는 프로그램입니다. ");

    DockerClient docker = DefaultDockerClient.fromEnv().build();
    Scanner scanner = new Scanner(System.in);
    System.out.print("컨테이너 이름을 입력하세요: ");
    String containerName = scanner.nextLine();
    ContainerInfo containerInfo = docker.inspectContainer(containerName);
    ContainerState containerState = containerInfo.state();
    String status = containerState.status();
    if (status.equals("exited")) {
      docker.startContainer(containerName);
      System.out.println(containerName + " 컨테이너를 시작합니다.");
    } else {
      System.out.println(containerName + " 컨테이너는 현재 " + status + " 상태입니다.");
    }
    docker.close();
  }
}
