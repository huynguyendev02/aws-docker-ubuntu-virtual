# Đồ án môn học: Điện toán đám mây

## Đề tài: Sử dụng Docker để ảo hoá server Ubuntu

## Table of content

- [Đồ án môn học: Điện toán đám mây](#đồ-án-môn-học:-điện-toán-đám-mây)
  - [Các thành viên nhóm](#các-thành-viên-nhóm-02)
  - [Công nghệ sử dụng](#công-nghệ-sử-dụng)
  - [Chức năng của ứng dụng](#chức-năng-của-ứng-dụng)
  - [Cài đặt ứng dụng](#cài-đặt-ứng-dụng)

## Các thành viên nhóm 02

- Nguyễn Gia Huy - 20110103 (Nhóm trưởng)
- Vũ Trung Hiếu - 20110482

## Công nghệ sử dụng

- Website: Java Servlet trên nền Tomcat Server 9
- Database: MySQL
- Ảo hoá: Docker

## Chức năng của ứng dụng

- Người dùng có thể tạo, khởi động, dừng và xoá instance
- Khi tạo instance, người dùng có thể điều chỉnh các thông số: tên instance, CPUs, memory, hệ điều hành, SSH, Network, Server, protection và userdata
- Instance khi bật protection không thể bị xoá
- Người dùng có thể kết nối đến instance bằng SSH Key hoặc SSH Password
- Người dùng có thể tạo, xoá và restore snapshot từ instance
- Người dùng có thể tạo, xoá image từ instance. Có thể tạo một instance mới từ image đã được tạo
- Người dùng có thể thêm, xoá network phục vụ các instance
- Admin có thể quản lý mọi thứ của mọi người dùng. Admin có thể thêm, xoá, sửa người dùng
- Admin có thể thêm server mới vào hệ thống cho người dùng sử dụng

## Cài đặt ứng dụng

- Bước 1: Tạo các instance trên EC2. Chọn một máy làm Host, tất cả máy còn lại dùng làm Worker
  ![image](https://user-images.githubusercontent.com/109943707/207302427-f3997c7f-b2ce-49a5-bd2f-6d5b9d4cb649.png)
- Bước 2: Tinh chỉnh Security cho máy Host có thể kết nối SSH và kết nối với các máy Worker còn lại
  ![image](https://user-images.githubusercontent.com/109943707/207302657-d95a8f40-fd2e-4abc-8dea-e6f26a7751a1.png)
- Bước 3: Cấp Elastic IP cho các máy (Optional)
  ![image](https://user-images.githubusercontent.com/109943707/207302736-21404887-d2e2-42b1-98ea-67094b31f9bb.png)
- Bước 4: Tiến hành SSH vào máy Host và các máy Worker để cài các ứng dụng cần thiết như Docker và các thành phần. Bật experimental để hỗ trợ tính năng snapshot.

```bash
git clone https://github.com/huynguyendev02/docker-ubuntu-virtual.git
cd docker-ubuntu-virtual
sudo chmod 777 ./HostInit.sh
./HostInit.sh
```

- Bước 5: Khởi động Docker Swarm trên máy host để các máy Worker có thể connect vào

```bash
docker swarm init --advertise-addr <MANAGER-IP>
```

- Bước 6: Chỉnh sửa file Config và tiến hành build file jar cho webserver
  ![image](https://user-images.githubusercontent.com/109943707/207303537-0c46bb1f-5910-4f90-ac12-7a2ebefabe80.png)
- Bước 7: Tiến hành build với Dockerfile bên dưới để tạo image chạy website (Website chạy trên nền tomcat 9.0)

```
FROM tomcat:9
COPY mysql-connector-j-8.0.31.jar /usr/local/tomcat/lib
COPY DockerUbuntuVirtual.war /usr/local/tomcat/webapps
COPY ubuntu-virutal-cloud.pem /usr/local/tomcat/webapps
EXPOSE 8080
CMD ["catalina.sh","run"]
```

- Bước 8: Tiến hành chạy Database và thêm dữ liệu vào với lệnh

```bash
docker run --name mysql -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=ubuntudockercloud -e MYSQL_PASSWORD=root --publish 3306:3306 -d --network hostnetwork mysql
cat ubuntudockercloud.sql | docker exec -i mysql /usr/bin/mysql -u root --password=root ubuntudockercloud
```

- Bước 9: Khởi chạy website với lệnh:

```bash
docker run -p 8080:8080 --network hostnetwork dockerhost
```

- Bước 10: Tiến hành kiểm tra ở đường dẫn:
  http://<your_host_ip>:8080/DockerUbuntuVirtual/
  ![image](https://user-images.githubusercontent.com/109943707/207308352-64c32a4a-4884-4bdc-818b-0a6d66a903b0.png)

## Tài liệu tham khảo

- https://docs.docker.com/
- https://phoenixnap.com/kb/how-to-ssh-into-docker-container
- https://thenewstack.io/how-to-enable-docker-experimental-features-and-encrypt-your-login-credentials/
- https://github.com/trancongtruong1234/Remote-docker-controller
