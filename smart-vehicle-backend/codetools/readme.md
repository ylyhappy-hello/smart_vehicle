依赖unzip
nix-shell -p unzip
source extract.sh
extract.sh ./*.zip
compact.sh 
