create table vam_order(
                      orderId varchar(50) primary key,
                      addressee varchar(50) not null,
                      memberId varchar(50),
                      memberAddr1 varchar(100) not null,
                      memberAddr2 varchar(100) not null,
                      memberAddr3 varchar(100) not null,
                      orderState varchar(30) not null,
                      deliveryCost int not null,
                      usePoint int not null,
                      orderDate timestamp default now(),
                      FOREIGN KEY (memberId)REFERENCES book_member(memberId)
                      );

create table vam_orderItem(
                          orderItemId int auto_increment primary key,
                          orderId varchar(50),
                          bookId int,
                          bookCount int not null,
                          bookPrice int not null,
                          bookDiscount int not null,
                          savePoint int not null,
                          FOREIGN KEY (orderId) REFERENCES vam_order(orderId),
                          FOREIGN KEY (bookId) REFERENCES vam_book(bookId)
                          );