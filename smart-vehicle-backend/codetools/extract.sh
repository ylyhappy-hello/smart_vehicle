function extract {
  for n in $@; do

    if [ ! -f "$n" ]; then
     echo "'$n' - file doesn't exist"
     return 1
    fi
    unzip $n -d ${n%.*}
    case "${n##.*}" in
      zip) 
        unzip $n -d ${n%.*}
        ;;
    esac
  done
}
