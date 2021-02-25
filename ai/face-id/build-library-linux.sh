#!/bin/bash

# 安装下载工具和构建工具
yum install -y wget bzip2 gcc perl gcc-gfortran autoconf libtool zlib-devel make automake

# 编译、构建并安装 gcc-5.5.0
tar -zxvf gcc-5.5.0.tar.gz
cd cd gcc-5.5.0
./contrib/download_prerequisites
mkdir build
cd build/
../configure --enable-checking=release --enable-languages=c,c++ --disable-multilib
make -j6
make install
ll /usr/lib64/libstdc++.so*
ll /usr/local/lib64/libstdc++.so*
rm /usr/lib64/libstdc++.so.6
ln -s /usr/local/lib64/libstdc++.so.6.0.21 /usr/lib64/libstdc++.so.6
cd ../..

# 编译、构建并安装 OpenBLAS
ln -s /usr/lib64/libgfortran.so.3.0.0 /usr/lib64/libgfortran.so
tar -zxvf openblas-v0.2.20.tar.gz
cd OpenBLAS-0.2.20/
make FC=gfortran
make PREFIX=/usr/local/openblas install
ln -s /usr/local/openblas/lib/libopenblas.so /usr/lib64/
ln -s /usr/local/openblas/lib/libopenblas.so.0 /usr/lib64/
cd ..

# 编译、构建并安装 OpenSSL
tar -zxvf openssl-1.0.0.tar.gz
cd openssl-1.0.0/
./config shared zlib-dynamic
make
ll *.so*
cp libssl.so.1.0.0 /usr/lib64/
cp libcrypto.so.1.0.0 /usr/lib64/
ln -s /usr/lib64/libssl.so.1.0.0 /usr/lib64/libssl.so
ln -s /usr/lib64/libcrypto.so.1.0.0 /usr/lib64/libcrypto.so
cd ..

# 编译、构建并安装 protobuf
tar -xjvf protobuf-2.6.0.tar.bz2
cd protobuf-2.6.0
./configure --prefix=/usr/local/protobuf
make
make install
ln -s /usr/local/protobuf/lib/libprotobuf.so /usr/lib64/
ln -s /usr/local/protobuf/lib/libprotobuf.so.9 /usr/lib64/
cd ..