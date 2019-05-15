#!/usr/bin/env ruby
Proto_root = "src/main/proto"
# assumes that hello-space-android and hello-space-java share same parent directory
Android_path = "../hello-space-android/app/src/main/java"
Backend_path = "src/main/java"
Proto_suffix = "_proto.proto"

def compile_proto(filename)
  `protoc --proto_path=#{Proto_root} --java_out=#{Backend_path} --javalite_out=#{Android_path} #{Proto_root}/#{filename}#{Proto_suffix}`
end

def main
  proto_files = [
    "location",
    "message",
    "package"
  ]
  
  for filename in proto_files
    compile_proto(filename)
  end
end

main
